package group;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.Customer;

/**
 * Servlet implementation class GroupCreationServlet
 */
@WebServlet("/group/GroupCreationServlet")
public class GroupCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupCreationServlet() {
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
		   // フォームからの入力を取得
	        String groupName = req.getParameter("group_name");
	        String description = req.getParameter("description");
	        System.out.println("groupName"+groupName);
	        System.out.println("description"+description);


	        // セッションからユーザー情報を取得
	        HttpSession session = req.getSession();
	        Customer cust = (Customer) session.getAttribute("cust");

	        // DAOを使用してグループをデータベースに追加
	        GroupDao groupDao = new GroupDao(); // GroupDaoのインスタンス化が必要です
	        try {
	            groupDao.connect(); // データベース接続
	            int groupId = groupDao.createGroup(groupName, description, cust.getNo());
	            // グループ作成成功の処理...
	           System.out.println("groupId取得まできた"+groupId);

	           // セッションに情報追加
               session.setAttribute("cust", cust);
               System.out.println("cust.getNo()"+cust.getNo());

               // 遷移先を指定
               next = "/group/add_customer.html";
	        } catch (Exception e) {
	        	// 遷移先を指定しエラーメッセージを詰める
                next = "/system/error.jsp";
                req.setAttribute("errmsg", e.getMessage());
                // ログにトレースを出力
                e.printStackTrace();
	        } finally {
	            try {
					groupDao.close();
				} catch (SQLException e) {
					 // 例外に対しては何も処理を行わない
					e.printStackTrace();
				} // データベース接続解除
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
