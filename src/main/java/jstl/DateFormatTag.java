package jstl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yana on 01.12.2015.
 */
public class DateFormatTag extends TagSupport {
    private String format;
    private Date date;

    public void setFormat(String format) {
        this.format = format;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (date != null)
                out.println(sdf.format(date));
        } catch (IOException e) {
            throw new JspException("Error: " + e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return SKIP_PAGE;
    }
}
