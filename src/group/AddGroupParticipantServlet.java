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
	         // 遷移先を指定
                next = "/system/home.jsp";
	        } catch (Exception e) {
	        	// 遷移先を指定しエラーメッセージを詰める
	            next = "/system/error.jsp";
	            req.setAttribute("errmsg", e.getMessage());
	            // ログにトレースを出力
	            e.printStackTrace();
	        } finally {
	            if (dao != null) {
	                try {
	                    dao.close();
	                    // 例外に対しては何も処理を行わない
	                } catch (SQLException e) {
	                }
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
