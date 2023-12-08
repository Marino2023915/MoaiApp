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
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // フォームからの入力を取得
	        String groupName = request.getParameter("group_name");
	        String description = request.getParameter("description");

	        // セッションからユーザー情報を取得
	        HttpSession session = request.getSession();
	        Customer customer = (Customer) session.getAttribute("cust");

	        // DAOを使用してグループをデータベースに追加
	        GroupDao groupDao = new GroupDao(); // GroupDaoのインスタンス化が必要です
	        try {
	            groupDao.connect(); // データベース接続
	            int groupId = groupDao.createGroup(groupName, description, customer.getNo());
	            // グループ作成成功の処理...
	           System.out.println("groupId取得まできた"+groupId);
	        } catch (Exception e) {
	            // エラー処理...
	        } finally {
	            try {
					groupDao.close();
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} // データベース接続解除
	        }
	    }




	  /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}

}
