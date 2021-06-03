package com.rose.note;

import com.rose.note.util.DBUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDB {
	// Use log factory to get log object
	private Logger logger = LoggerFactory.getLogger(TestDB.class);
	@Test
	public void testDB() {
		System.out.println(DBUtil.getConnection());
		// Use log
		logger.info("get db connection: " + DBUtil.getConnection());
		logger.info("get db connection: {}", DBUtil.getConnection());
	}
}
