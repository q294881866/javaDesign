package javaSe.netORio.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface HttpHandler {
	/** ����{@link SelectionKey#OP_CONNECT}�¼� */
	void connect(SelectionKey key) throws IOException;
	
	/** ����{@link SelectionKey#OP_ACCEPT}�¼� */
	void accept(SelectionKey key) throws IOException;

	/** ����{@link SelectionKey#OP_READ}�¼� */
	void read(SelectionKey key) throws IOException;

	/** ����{@link SelectionKey#OP_WRITE}�¼� */
	void write(SelectionKey key) throws IOException;
}