package com.zia.easybookmodule.engine.strategy;

import com.zia.easybookmodule.bean.Book;
import com.zia.easybookmodule.bean.Chapter;

import java.io.*;
import java.util.List;

/**
 * Created by zia on 2019/4/6.
 */
public class TxtParser implements ParseStrategy {
    @Override
    public String parseContent(Chapter chapter) {
        StringBuilder sb = new StringBuilder();
        sb.append(chapter.getChapterName());
        sb.append("\n");
        for (String line : chapter.getContents()) {
            //4个空格+正文+换行
            sb.append("        ");
            sb.append(line);
            sb.append("\n");
        }
        //章节结束空一行，用来分割下一章节
//        sb.append("\n");
        return sb.toString();
    }

    @Override
    public File save(List<Chapter> chapters, Book book, String savePath) throws IOException {
        String bookName = book.getBookName() + "-" + book.getSiteName();
        savePath += File.separator + bookName + ".txt";
        File file = new File(savePath);
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file)));
        for (Chapter chapter : chapters) {
            bufferedWriter.write(parseContent(chapter));
        }
        return file;
    }
}
