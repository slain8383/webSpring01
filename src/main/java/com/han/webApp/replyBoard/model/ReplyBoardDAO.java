package com.han.webApp.replyBoard.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.han.webApp.Constant;
import com.han.webApp.DBCP;

public class ReplyBoardDAO extends DBCP{
	public static ReplyBoardDAO dao = new ReplyBoardDAO();
	public static ReplyBoardDAO getInstance(){
		return dao;
	}
	
	public JdbcTemplate template;
	public JdbcTemplate getTemplate() {
		return template;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public ReplyBoardDAO(){
		template = Constant.template;
	}
	
	//�۸��
	public List<ReplyBoardVO> replyList(){	//��ӹ��� �������̽��� �޼��� �������̵�
		
		String sql = "select num, username, subject, hit, writedate, lev"
				+ " from replyboard order by ref desc, step asc";
		return (ArrayList<ReplyBoardVO>)template.query(sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));
	}
	
	//�۵��
	public int replyWrite(final ReplyBoardVO vo){

		String sql = "insert into replyboard(num, username, subject, content, userip, ref) "
					+ "values(replyBoardSq.nextVal,?,?,?,?,replyBoardSq.currVal)";
		return template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, vo.getUsername());
				pstmt.setString(2, vo.getSubject());
				pstmt.setString(3, vo.getContent());
				pstmt.setString(4, vo.getUserip());
			}
		});
	}
	
	//�ѷ��ڵ��
	public int getTotalRecored(){
		String sql = "select count(num) cnt from replyboard";
		ReplyBoardVO vo = template.queryForObject(sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));
		return vo.getCnt();
	}
	
	//��ȸ�� ����
	public void hitCount(final int num){
		String sql = "update replyBoard set hit = hit+1 where num=?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, num);
			}
		});
	}
	
	//���ڵ弱��
	public ReplyBoardVO select(int num){
		String sql = "select num, username, subject, content, hit, writedate, userip "
				   + "from replyBoard where num="+num;
		return template.queryForObject(sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));
	}
	
	//���ڵ� ����
	public int update(final ReplyBoardVO vo) {
		String sql = "update replyBoard set username=?, subject=?, content=? where num=?";
		return template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, vo.getUsername());
				pstmt.setString(2, vo.getSubject());
				pstmt.setString(3, vo.getContent());
				pstmt.setInt(4, vo.getNum());
			}
		});
	}
	
	//�亯�� ���� (Ʈ������ �̿�)
	public int replyInsert(final ReplyBoardVO vo){
		
		//1.�� ���� ref, lev, step ���
		String sql = "select ref, lev, step from replyBoard where num="+vo.getNum();
			//ref, lev, step�� ���� vo2�� ���
		final ReplyBoardVO vo2 = template.queryForObject(sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));
		
		//2.step ����
		sql = "update replyBoard set step = step+1 where ref=? and step>?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, vo2.getRef());
				ps.setInt(2, vo2.getStep());
			}
		});
		
		//3.insert ���ڵ� �߰�, �亯�� ���� �ܰ�
		sql = "insert into replyBoard(num,username,subject,content,userip,ref,lev,step) "
				+ "values(replyBoardSq.nextVal,?,?,?,?,?,?,?)";
		return template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, vo.getUsername());
				pstmt.setString(2, vo.getSubject());
				pstmt.setString(3, vo.getContent());
				pstmt.setString(4, vo.getUserip());
				pstmt.setInt(5, vo2.getRef());  //���۰� ���� Ref
				pstmt.setInt(6, vo2.getLev()+1);  //���� Lev +1
				pstmt.setInt(7, vo2.getStep()+1);  //���� Step +1
			}
		});
	}
	
	//���ڵ� ���� - ������ �����Ǹ� ����� ��� ���������ֵ��� �ϱ�
	public int delete(final int num){
		
		//������ ���ڵ��� lev���ϱ� = �������� �亯������ �ľ�
		String sql = "select lev,ref from replyBoard where num="+num;
		final ReplyBoardVO vo = template.queryForObject(sql, new BeanPropertyRowMapper<ReplyBoardVO>(ReplyBoardVO.class));
		int cnt = 0;
		
		//�������� �亯�������� ���� ��������
		if(vo.getLev() == 0){  //����
			sql = "delete from replyBoard where ref=?";
			cnt = template.update(sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, vo.getRef());
				}
			});
			
		}else{  //�亯��
			sql = "update replyBoard set username=?, subject=?, content=? where num=?";
			cnt = template.update(sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, " ");
					ps.setString(2, "������ ���Դϴ�.");
					ps.setString(3, "������ ���Դϴ�.");
					ps.setInt(4, num);
				}
			});
		}
		return cnt;
	}
}

/* //DBCP���
public static ReplyBoardDAO dao= new ReplyBoardDAO();
public static ReplyBoardDAO getInstance(){
	return dao;
}
//�۸��
public List<ReplyBoardVO> replyList(){	//��ӹ��� �������̽��� �޼��� �������̵�
	
	ArrayList<ReplyBoardVO> list = new ArrayList<ReplyBoardVO>();
	try{
		conn = getConnection();
		String sql = "select num, username, subject, hit, writedate, lev"
				   + " from replyboard order by ref desc, step asc";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			ReplyBoardVO vo = new ReplyBoardVO();
			vo.setNum(rs.getInt(1));
			vo.setUsername(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setHit(rs.getInt(4));
			vo.setWritedate(rs.getString(5));
			vo.setLev(rs.getInt(6));
			list.add(vo);
		}
	}catch(Exception e){
		System.out.println("���ڵ� ���� ����! : "+e.getMessage());
	}finally{
		dbClose();
	}	
	return list;
}

//�۵��
public void replyWrite(ReplyBoardVO vo){
	try{
		conn = getConnection();
		String sql = "insert into replyboard(num, username, subject, content, userip, ref) "
					+ "values(replyBoardSq.nextVal,?,?,?,?,replyBoardSq.currVal)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUsername());
		pstmt.setString(2, vo.getSubject());
		pstmt.setString(3, vo.getContent());
		pstmt.setString(4, vo.getUserip());
		
		vo.setCnt(pstmt.executeUpdate());
	}catch(Exception e){
		System.out.println("���ڵ� �߰� ���� = " + e.getMessage());
	}finally{
		dbClose();
	}
}

//�ѷ��ڵ��
public int getTotalRecored(){
	int cnt = 0;
	try{
		conn = getConnection();
		String sql = "select count(num) from replyboard";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()){
			cnt = rs.getInt(1);
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		dbClose();
	}
	return cnt;
}
//���ڵ弱��
public ReplyBoardVO select(int num){
	ReplyBoardVO vo = new ReplyBoardVO();
	try{
		conn = getConnection();
		
		//��ȸ�� ī��Ʈ ����
		String sql2 = "update replyBoard set hit = hit+1 where num=?";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setInt(1, num);
		pstmt.executeUpdate();
		
		String sql = "select num, username, subject, content, hit, writedate, userip "
				   + "from replyBoard where num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		if(rs.next()){
			vo.setNum(rs.getInt(1));
			vo.setUsername(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setHit(rs.getInt(5));
			vo.setWritedate(rs.getString(6));
			vo.setUserip(rs.getString(7));
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		dbClose();
	}
	return vo;
}
//���ڵ� ����
public void update(ReplyBoardVO vo){
	try{
		conn = getConnection();
		String sql = "update replyBoard set username=?, subject=?, content=? where num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUsername());
		pstmt.setString(2, vo.getSubject());
		pstmt.setString(3, vo.getContent());
		pstmt.setInt(4, vo.getNum());
		vo.setCnt(pstmt.executeUpdate());
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		dbClose();
	}
}
//�亯�� ���� (Ʈ������ �̿�)
public void replyInsert(ReplyBoardVO vo){
	conn = getConnection();
	int updateCnt = 0;  //update���
	int insertCnt = 0;  //insert���
	
	try{
		//�ڵ� Ŀ�� ���
		conn.setAutoCommit(false);
		
		//1.�� ���� ref, lev, step ���
		String sql = "select ref, lev, step from replyBoard where num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, vo.getNum());
		rs = pstmt.executeQuery();
		rs.next();
		int ref = rs.getInt(1);
		int lev = rs.getInt(2);
		int step = rs.getInt(3);
		
		//2.step ����
		sql = "update replyBoard set step = step+1 where ref=? and step>?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, ref);
		pstmt.setInt(2, step);
		updateCnt = pstmt.executeUpdate();
		
		//3.insert ���ڵ� �߰�, �亯�� ���� �ܰ�
		sql = "insert into replyBoard(num,username,subject,content,userip,ref,lev,step) "
				+ "values(replyBoardSq.nextVal,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getUsername());
		pstmt.setString(2, vo.getSubject());
		pstmt.setString(3, vo.getContent());
		pstmt.setString(4, vo.getUserip());
		pstmt.setInt(5, ref);
		pstmt.setInt(6, lev+1);
		pstmt.setInt(7, step+1);
		insertCnt = pstmt.executeUpdate();
		vo.setCnt(insertCnt);
		
		conn.commit();	//����,�̻���� ����Ǹ� commit
	}catch(Exception e){
		try{ //���ܰ� �߻��ϸ� ������ �������
		conn.rollback();
		}catch(Exception x){
			System.out.println("1"+x.getMessage());
		}
		System.out.println("2"+e.getMessage());
	}finally{
		dbClose();
		try{
		conn.setAutoCommit(true);
		}catch(Exception c){
			System.out.println("3"+c.getMessage());
		}
		dbClose();
	}
}
//���ڵ� ���� //������ �����Ǹ� ����� ��� ���������ֵ��� �ϱ�
public int delete(int num){
	int cnt = 0;
	try{
		conn = getConnection();
		//������ ���ڵ��� lev���ϱ� = �������� �ľ�
		String sql = "select lev,ref from replyBoard where num=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			if(rs.getInt(1)==0){  //lev�� 0 :����
				sql = "delete from replyBoard where ref=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt(2));
				cnt = pstmt.executeUpdate();
			}else{  //����̶�� �Ʒ��� �������� ��ü
				sql = "update replyBoard set username=?, subject=?, content=? where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "�����");
				pstmt.setString(2, "������ ���Դϴ�.");
				pstmt.setString(3, "-");
				pstmt.setInt(4, num);
				cnt = pstmt.executeUpdate();
			}
		}
	}catch(Exception e){
		e.getMessage();
	}finally{
		dbClose();
	}
	return cnt;
}
}*/
