package net.tslat.aoa3.utils;

import net.minecraftforge.fml.common.Loader;
import net.tslat.aoa3.advent.AdventOfAscension;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Consumer;

public class FileUtil {
	public static void walkAllAvailableResourceFiles(String fileType, String modId, String folderPath, @Nullable String backupFolderPath, Consumer<BufferedReader> consumer) {
		Object modContainer = Loader.instance().getIndexedModList().get(modId);
		boolean fallbackRequired = false;

		if (modContainer == null) {
			AdventOfAscension.logMessage(Level.ERROR, "Unable to find mod with domain: " + modId);

			return;
		}

		URL resourceURL = modContainer.getClass().getResource("/assets/" + modId + "/" + folderPath);

		if (resourceURL == null) {
			if (backupFolderPath != null)
				resourceURL = modContainer.getClass().getResource("/assets/" + modId + "/" + backupFolderPath);

			if (resourceURL == null) {
				AdventOfAscension.logOptionalMessage("Unable to find resource from mod " + modId + " with path: " + folderPath + (backupFolderPath != null ? " or backup path: " + backupFolderPath : ""));

				return;
			}

			fallbackRequired = true;
		}

		FileSystem fs = null;

		try {
			URI resourceURI = resourceURL.toURI();
			Path path;

			if ("file".equalsIgnoreCase(resourceURI.getScheme())) {
				path = Paths.get(resourceURI);
			}
			else {
				fs = safelyGetFileSystem(resourceURI);

				if (fs == null)
					throw new FileSystemNotFoundException();

				path = fs.getPath("/assets/" + modId + "/" + (fallbackRequired ? backupFolderPath : folderPath));
			}

			Iterator<Path> resourceFiles = Files.walk(path).iterator();

			while (resourceFiles.hasNext()) {
				Path resourcePath = resourceFiles.next();

				if (fileType.equals(FilenameUtils.getExtension(resourcePath.toString()))) {
					try (BufferedReader reader = Files.newBufferedReader(resourcePath)) {
						consumer.accept(reader);
					}
				}
			}
		}
		catch (URISyntaxException ex) {
			AdventOfAscension.logMessage(Level.ERROR, "Invalid URI syntax for resource search. Mod ID: " + modId + ", path: " + resourceURL.toString());

			ex.printStackTrace();
		}
		catch (IOException ex) {
			AdventOfAscension.logMessage(Level.ERROR, "Error reading resource file during file walk. Mod ID: " + modId + ", path: " + resourceURL.toString());
		}
		catch (FileSystemNotFoundException ex) {
			AdventOfAscension.logOptionalMessage("FileSystem error - somehow managed to find an existing filesystem without one present.", ex);
		}
		finally {
			try {
				if (fs != null)
					fs.close();
			}
			catch (IOException ex) {
				AdventOfAscension.logOptionalMessage("Failed to close filesystem after file walk. This could be an issue.", ex);
			}
		}
	}

	public static void walkAllAvailableFiles(String fileType, String folderPath, @Nullable String backupFolderPath, Consumer<BufferedReader> consumer) {
		File folder = new File(FilenameUtils.separatorsToSystem(folderPath));

		if (!folder.exists()) {
			if (backupFolderPath != null)
				folder = new File(FilenameUtils.separatorsToSystem(backupFolderPath));

			if (!folder.exists()) {
				AdventOfAscension.logOptionalMessage("Unable to find folder for file walk: " + folderPath + (backupFolderPath != null ? ". Also tried backup location: " + backupFolderPath : ""));

				return;
			}
			else if (!folder.isDirectory()) {
				AdventOfAscension.logOptionalMessage("Filepath does not point to valid directory: " + folderPath);

				return;
			}
		}

		try {
			Iterator<Path> files = Files.walk(folder.toPath()).iterator();

			while (files.hasNext()) {
				Path filePath = files.next();

				if (fileType.equals(FilenameUtils.getExtension(filePath.toString()))) {
					try (BufferedReader reader = Files.newBufferedReader(filePath)) {
						consumer.accept(reader);
					}
				}
			}
		}
		catch (IOException ex) {
			AdventOfAscension.logOptionalMessage("Error reading file during file walk. Path: " + folder.getAbsolutePath(), ex);
		}
	}

	@Nullable
	public static String getTextFromResourceFile(String modId, String assetFilePath, @Nullable String backupFilePath) {
		Object modContainer = Loader.instance().getIndexedModList().get(modId);

		if (modContainer == null) {
			AdventOfAscension.logMessage(Level.ERROR, "Unable to find mod with domain: " + modId);

			return null;
		}

		ClassLoader loader = modContainer.getClass().getClassLoader();
		InputStream streamIn = loader.getResourceAsStream("assets/" + modId + "/" + assetFilePath);

		if (streamIn == null) {
			if (backupFilePath != null)
				streamIn = loader.getResourceAsStream("assets/" + modId + "/" + backupFilePath);

			if (streamIn == null) {
				AdventOfAscension.logOptionalMessage("Unable to find resource from mod " + modId + " with path: " + assetFilePath + (backupFilePath != null ? " or backup path: " + backupFilePath : ""));

				return null;
			}
		}

		try (InputStreamReader streamReader = new InputStreamReader(streamIn, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {
			return bufferedReaderToString(reader);
		}
		catch (IOException ex) {
			AdventOfAscension.logMessage(Level.ERROR, "Buffered reader failed for mod: " + modId, ex);
		}

		return null;
	}

	@Nullable
	public static String getTextFromFile(String filePath, @Nullable String backupFilePath) {
		File file = new File(FilenameUtils.separatorsToSystem(filePath));

		if (!file.exists()) {
			if (backupFilePath != null)
				file = new File(FilenameUtils.separatorsToSystem(backupFilePath));

			if (!file.exists()) {
				AdventOfAscension.logOptionalMessage("Unable to find requested file: " + filePath + (backupFilePath != null ? ". Also tried to find " + backupFilePath : ""));

				return null;
			}
		}

		try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
			return bufferedReaderToString(reader);
		}
		catch (IOException ex) {
			AdventOfAscension.logOptionalMessage("Error reading file: " + file.getAbsolutePath(), ex);
		}

		return null;
	}

	public static String bufferedReaderToString(BufferedReader reader) {
		final StringBuilder content = new StringBuilder();

		reader.lines().forEach(line -> {
			content.append(line);
			content.append("\n");
		});

		return content.toString();
	}

	@Nullable
	private static FileSystem safelyGetFileSystem(URI uri) throws IOException {
		String scheme = uri.getScheme();

		try {
			for (FileSystemProvider provider : FileSystemProvider.installedProviders()) {
				if (scheme.equalsIgnoreCase(provider.getScheme()))
					return provider.getFileSystem(uri);
			}
		}
		catch (FileSystemNotFoundException ex) {
			return FileSystems.newFileSystem(uri, Collections.emptyMap());
		}

		return null;
	}
}
