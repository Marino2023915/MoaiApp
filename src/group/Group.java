/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package group;

import java.util.Date;
import java.util.List;

/**
 * 顧客情報を保持します。
 * <br><br>
 * 【Information】<br>
 *  機能１ すべてのメンバ変数，メソッドを作成します。<br>
 *
 * @author nus Academy Co., Ltd
 */
public class Group {
    /** グループid */
    private Integer group_No;

    /** 顧客姓 */
    private String group_Name;

    /** 顧客名 */
    private String description;

    /** 顧客パスワード */
    private String owner_Id;

    /** 顧客郵便番号 */
    private Date creation_Date;

<<<<<<< HEAD

    // メンバー名のリストを保持するためのフィールド
    private List<String> memberNames;


=======
 // メンバー名のリストを保持するためのフィールド
    private List<String> memberNames;

>>>>>>> master
	public Integer getGroup_No() {
		return group_No;
	}

	public void setGroup_No(Integer group_No) {
		this.group_No = group_No;
	}

	public String getGroup_Name() {
		return group_Name;
	}

	public void setGroup_Name(String group_Name) {
		this.group_Name = group_Name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner_Id() {
		return owner_Id;
	}

	public void setOwner_Id(String owner_Id) {
		this.owner_Id = owner_Id;
	}

	public Date getCreation_Date() {
		return creation_Date;
	}

	public void setCreation_Date(Date creation_Date) {
		this.creation_Date = creation_Date;
	}


	// メンバー名のリストを設定するメソッド
    public void setMemberNames(List<String> memberNames) {
        this.memberNames = memberNames;
    }

    // メンバー名のリストを取得するメソッド
    public List<String> getMemberNames() {
        return memberNames;
    }


	 // メンバー名のリストを設定するメソッド
    public void setMemberNames(List<String> memberNames) {
        this.memberNames = memberNames;
    }

    // メンバー名のリストを取得するメソッド
    public List<String> getMemberNames() {
        return memberNames;
    }



}
