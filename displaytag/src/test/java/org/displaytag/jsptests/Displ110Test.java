package org.displaytag.jsptests;

import org.displaytag.test.DisplaytagCase;
import org.junit.Assert;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.TableRow;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;


/**
 * Test for DISPL-110 - Ability to highlight selected table row
 * @author Fabrizio Giustina
 * @version $Id$
 */
public class Displ110Test extends DisplaytagCase
{

    /**
     * @see org.displaytag.test.DisplaytagCase#getJspName()
     */
    public String getJspName()
    {
        return "DISPL-110.jsp";
    }

    /**
     * @param jspName jsp name, with full path
     * @throws Exception any axception thrown during test.
     */
    @Test
    public void doTest() throws Exception
    {

        WebRequest request = new GetMethodWebRequest(getJspUrl(getJspName()));

        WebResponse response = runner.getResponse(request);

        if (log.isDebugEnabled())
        {
            log.debug(response.getText());
        }

        WebTable[] tables = response.getTables();
        Assert.assertEquals("Wrong number of tables.", 1, tables.length);

        Assert.assertEquals("Wrong number of columns.", 2, tables[0].getColumnCount());
        Assert.assertEquals("Wrong number of rows.", 3, tables[0].getRowCount()); // 2 plus header

        TableRow[] rows = tables[0].getRows();

        Assert.assertEquals("Wrong id for row 1", "idcamel0", rows[1].getID());
        Assert.assertEquals("Wrong id for row 2", "idcamel1", rows[2].getID());

        Assert.assertEquals("Wrong class for row 1", "odd classcamel0", rows[1].getClassName());
        Assert.assertEquals("Wrong class for row 2", "even classcamel1", rows[2].getClassName());

    }
}