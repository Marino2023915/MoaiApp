package group;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GroupCreationServlet
 */
@WebServlet("/group/AddGroupParticipantServlet")
public class AddGroupParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGroupParticipantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		  req.setCharacterEncoding("UTF-8");
		  String next = null;
	        // エラーメッセージ
	        String errmsg = null;
	        String groupId = req.getParameter("group_id");
	        String customerNo = req.getParameter("customer_no");

	        GroupParticipantDao dao = new GroupParticipantDao();
	        try {
	            dao.connect();
	            dao.addParticipant(groupId, customerNo);
	            // 成功した場合の処理（リダイレクトや成功メッセージの表示など）
	            System.out.println("模合group"+groupId+"に+"+"customerNo"+customerNo+"を追加しました");
	        } catch (Exception e) {
	            // エラー処理
	            e.printStackTrace();
	        } finally {
	            try {
					dao.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
	        }
	        // 画面遷移
	        req.getRequestDispatcher(next).forward(req, res);
	    }





	  /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

}
