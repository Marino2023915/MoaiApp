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

<%--useBean--%>
<jsp:useBean id="errmsg" scope="request" type="java.lang.String" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー画面【MoaiApp】</title>
<link href="../css/common.css" rel="stylesheet">
</head>
<body>
<!-- ヘッダ -->
  <header>
    <div class="contents-wrap-title">
      <div class="site-title">
        <h1>MoaiApp</h1>
        <span class="site-subtitle"></span>
      </div>
      <h2>エラー画面</h2>
    </div>
    <hr class="hr-header">
  </header>
<!-- メインコンテンツ -->
  <main>
    <div class="message">
      <p>
         不具合が発生しました。以下のメッセージをご確認ください。<br>
        ※遷移前の画面にはブラウザの戻るボタンから，戻ってください。
      </p>
      <p class="error"><%=errmsg %></p>
    </div>
  </main>
<!-- フッタ -->
  <footer>
    <hr>
    <p>
      お問い合わせ：仲宗根<br>
      <a href="mailto: ">Mail:marino.nakasone@gmail.com</a>
    </p>
    <p>marino.nakasone 2023, 1205.</p>
  </footer>
</body>
</html>
