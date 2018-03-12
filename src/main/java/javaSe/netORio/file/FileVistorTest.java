package javaSe.netORio.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVistorTest {
	private static int layer = 0;
	private static String turn="�� ";
	private static StringBuilder space=new StringBuilder();
	
	public static String getSpace(int layer) {
		space.delete(0, space.length());
		for (int i = 0; i < layer; i++) {
			space.append("��");
		}
		space.append(turn);
		return space.toString();
	}

	public static void main(String[] args) throws IOException {
		Path file = Paths.get("F:/", "root");
		Files.walkFileTree(file,new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				System.out.format(getSpace(layer)+"��ʼ����Ŀ¼ %s \n", dir);
				layer++;
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				System.out.format(getSpace(layer)+"��ǰ���ʵ��ļ� %s \n", file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				System.out.format(getSpace(layer)+"�ļ�����ʧ�� %s \n", file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				layer--;
				System.out.format(getSpace(layer)+"��������Ŀ¼ %s \n", dir);
				return FileVisitResult.CONTINUE;
			}
		});

	}
	
}