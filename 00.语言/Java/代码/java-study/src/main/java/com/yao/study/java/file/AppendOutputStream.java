package com.yao.study.java.file;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 针对追加模式进行数据输入的情况，需于再次写对象前，移除头文件的内容
 *
 * Created by Administrator on 2015/1/16.
 */
public class AppendOutputStream extends ObjectOutputStream {
    public AppendOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    protected AppendOutputStream() throws IOException, SecurityException {
        super();
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        return;
    }
}
