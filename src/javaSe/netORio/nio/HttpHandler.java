package javaSe.netORio.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;

public interface HttpHandler {
	/** 处理{@link SelectionKey#OP_CONNECT}事件 */
	void connect(SelectionKey key) throws IOException;
	
	/** 处理{@link SelectionKey#OP_ACCEPT}事件 */
	void accept(SelectionKey key) throws IOException;

	/** 处理{@link SelectionKey#OP_READ}事件 */
	void read(SelectionKey key) throws IOException;

	/** 处理{@link SelectionKey#OP_WRITE}事件 */
	void write(SelectionKey key) throws IOException;
}