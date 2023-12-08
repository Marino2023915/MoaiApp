<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム【MoaiApp】</title>
<link href="../css/common.css" rel="stylesheet">
<script>
function generateNameFields() {
  var numberOfPeople = document.getElementById("number_of_people").value;
  var container = document.getElementById("name_fields_container");
  container.innerHTML = ""; // コンテナの内容をクリア


  for (var i = 0; i < numberOfPeople; i++) {
	  var input = document.createElement("input"); // 新しい<input>要素を作成
	  input.type = "text"; // 入力フィールドのタイプをテキストに設定
	  input.name = "person_name_" + i; // 各入力フィールドに一意の名前を設定
	  input.placeholder = "名前 " // プレースホルダーに "名前 x" というテキストを設定
	  container.appendChild(input); // <input>要素をコンテナに追加
	  container.appendChild(document.createElement("br")); // 改行をコンテナに追加

  }
}
</script>
</head>
<body>
  <!-- ヘッダ -->
  <header>
    <div class="contents-wrap-title">
      <div class="site-title">
        <h1>MoaiApp</h1>
        <span class="site-subtitle"></span>
      </div>
      模合管理システムです

    </div>
    <hr class="hr-header">
  </header>
<!-- メインコンテンツ" -->
  <main>
    <h1>NEW画面登録</h1>

    <form method="post" action="/MoaiApp/payment/PaymentServlet">
      <div>
        <label for="customer_name">模合グループ名:</label>
        <input type="text" id="customer_name" name="group_name">
      </div>

      <div>
 		 <label for="amount">毎月の金額:</label>
 		 <input type="number" id="amount" name="amount" min="1000" max="30000000" step="1000"><span class="unit-label">円</span>
		</div>

       <div>
	    <label for="date">いつから開始:</label>
	    <input type="date" id="date" name="date">
		</div>

      <div>
        <label for="note">備考:</label>
        <textarea id="note" name="note"></textarea>
      </div>


	    <div>
	  <label for="number_of_people">人数:</label>
	  <select id="number_of_people" name="number_of_people" onchange="generateNameFields()">
	    <option value="">選択してください</option>
	    <option value="1">1人</option>
	    <option value="2">2人</option>
	    <option value="3">3人</option>
	    <option value="4">4人</option>
	    <option value="5">5人</option>
	    <option value="6">6人</option>
	    <option value="7">7人</option>
	    <option value="8">8人</option>
	    <option value="9">9人</option>
	    <option value="10">10人</option>
	    <!-- 必要なだけオプションを追加 -->
	  </select>
		</div>

    <div id="name_fields_container">
      <!-- JavaScriptによって生成された名前入力フィールドがここに挿入される -->
    </div>
  <input type="submit" value="登録">
  </form>

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