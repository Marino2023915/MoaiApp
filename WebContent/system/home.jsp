<!--
**
* Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
*
* This software is the proprietary information of nus Academy Co., Ltd.
* Use is subject to license terms.
**
-->
<%--encoding--%>
<%@ page pageEncoding="UTF-8"
  contentType="text/html;charset=UTF-8" %>
<%@ page import="customer.Customer" %>
<%--useBean--%>
<jsp:useBean id="cust" scope="session" type="customer.Customer" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム【さくらマーケット】</title>
<link href="../css/common.css" rel="stylesheet">
</head>
<body>
<!-- ヘッダ -->
  <header>
    <div class="contents-wrap-title">
      <div class="site-title">
        <h1>さくらマーケット</h1>
        <span class="site-subtitle">〜インターネットショッピングサイト〜</span>
      </div>
      <h2>ホーム</h2>
      <div class="logout"><a href="/SAKURA_ans/system/LogoutServlet">ログアウト</a></div>
    </div>
    <table class="table-menu">
      <tr>
        <th class="menu-selected">
          ホーム
        </th>
        <th>
          <a href="/SAKURA_ans/goods/GoodsSearchServlet">商品一覧</a>
        </th>
        <th>
          <a href="/SAKURA_ans/goods/goods_search.html">商品検索</a>
        </th>
        <th>
          <a href="/SAKURA_ans/order/ViewCartServlet">買い物かご</a>
        </th>
      </tr>
    </table>
  </header>
  <!-- メインコンテンツ -->
  <main>
    <div class="message">
      いらっしゃいませ，
      <span class="custmer-name">
        <jsp:getProperty name="cust" property="nameLast" />
        <jsp:getProperty name="cust" property="nameFirst" />
      </span>
      様。
      <p>
        商品一覧を選ぶと，商品の一覧が表示されます。<br>
        ご希望の商品を買い物かごに入れて，ご注文ください。
      </p>
    </div>
  </main>
  <!-- フッタ -->
  <footer>
    <hr>
    <p>
      お問い合わせ：さくらマーケットお客様センター<br>
      <a href="mailto: ">Mail:xxx@nus.com</a> Tel:03-XXXX-XXXX
    </p>
    <p>Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.</p>
  </footer>
</body>
</html>
