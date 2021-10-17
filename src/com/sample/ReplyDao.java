package com.sample;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao {

	// INSERT
		private static void insert(Reply rep) throws SQLException {

			String url = "jdbc:mysql://localhost:3306/grape";
			String user = "root";
			String password = "password";

			Connection con = DriverManager.getConnection(url, user, password);

			PreparedStatement ps = con.prepareStatement("insert into employee values(?, ?, ?, ?, ?)");
			ps.setInt(1, rep.getComId());
			ps.setInt(2, rep.getRepId());
			ps.setDate(3, new Date(rep.getRepDate().getTime()));
			ps.setString(4, rep.getRepName());
			ps.setString(4, rep.getRepContent());

			ps.execute();

		}

		// SELECT
		private static List<Reply> allReply(int comId) throws SQLException {

			String url = "jdbc:mysql://localhost:3306/grape";
			String user = "root";
			String password = "password";

			Connection con = DriverManager.getConnection(url, user, password);

			PreparedStatement ps = con.prepareStatement("select * from reply where comId = ?");
			ResultSet rs = ps.executeQuery();

			List<Reply> list = new ArrayList<>();

			try {
				while (rs.next()) {
					Reply rep = new Reply();
					rep.setComId(rs.getInt("comId"));
					rep.setRepId(rs.getInt("repId"));
					rep.setRepDate(rs.getDate("repDate"));
					rep.setRepName(rs.getString("repName"));
					rep.setRepContent(rs.getString("repContent"));
					list.add(rep);
				}
			} finally {
				con.close();
			}

			return list;

		}

}