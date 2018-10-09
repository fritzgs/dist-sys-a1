import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.jupiter.api.Test;


class SQLTest {

	@Test
	void test() throws SQLException
	{
		SQLHandler sqlh = new SQLHandler();
		sqlh.alterEntry(sqlh.getConnection(), "1", "2", "asdas", "afwe", "cza", "hdtew", "adqw");
	}

}
