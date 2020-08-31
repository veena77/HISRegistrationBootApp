package com.ashokit.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class SSNCustomGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		String prefix="20";
		String suffix="";
		try {
			Connection con=session.connection();
			Statement stmt=con.createStatement();
			String sql="SELECT CANDIDATE_SEQ.NEXTVAL FROM DUAL";
			ResultSet rs=stmt.executeQuery(sql);
		
			if(rs.next()) {
					Integer seqval=rs.getInt(1);
	//		 suffix=Integer.toString(seqval);
			 
					String strseqval=String.valueOf(seqval);
				
			suffix=strseqval.substring(1, 3)+" "+strseqval.substring(3);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prefix+" "+suffix;
	}

}
