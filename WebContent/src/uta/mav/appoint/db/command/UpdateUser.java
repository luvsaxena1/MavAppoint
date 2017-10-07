package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import uta.mav.appoint.login.*;

public class UpdateUser extends SQLCmd {
	
	private LoginUser loginUser;
	
	Boolean b;
	
	public UpdateUser(LoginUser loginUser){
		this.loginUser = loginUser;
		
		SQLCmd sqlCmd = new DeleteMajorsByUserId(loginUser.getUserId());
		sqlCmd.execute();
		sqlCmd = new AddMajorsByUserId(loginUser.getUserId(), loginUser.getMajors());
		sqlCmd.execute();
		
		sqlCmd = new DeleteDepartmentsByUserId(loginUser.getUserId());
		sqlCmd.execute();
		sqlCmd = new AddDepartmentsByUserId(loginUser.getUserId(), loginUser.getDepartments());
		sqlCmd.execute();
		
		b = false;
	}
	
	public void queryDB(){
		try{
			String command = "UPDATE User SET email = ?, password = ?, role = ?, validated = ? WHERE userId=?";
			PreparedStatement statement = conn.prepareStatement(command);
			int i=1;
			statement.setString(1,loginUser.getEmail());
			statement.setString(2,loginUser.getPassword());
			statement.setString(3,loginUser.getRole());
			statement.setInt(4,loginUser.getValidated());
			statement.setInt(5,loginUser.getUserId());

			int res = statement.executeUpdate();
			System.out.println(res);
			b=true;
		}
		catch(SQLException sq){
			//System.out.println(sq.toString());
		}
	}
	
	public void processResult(){
		result.add(b);
	}

}
