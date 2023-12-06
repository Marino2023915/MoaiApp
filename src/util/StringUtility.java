/**
 * Copyright nus Academy Co., Ltd. 2008, 2020. All rights reserved.
 *
 * This software is the proprietary information of nus Academy Co., Ltd.
 * Use is subject to license terms.
 */
package util;

/**
 * 文字列操作用のユーティリティクラスです。
 *
 * @author nus Academy Co., Ltd
 */
public class StringUtility {
    /**
     * 文字列を，指定された総文字数に，<br>
     * 指定された文字で右詰めによりpaddingします。<br>
     * 例:1→000001
     * <br><br>
     * <br>【Example】
     * <pre>{@literal
     * //1→0000000001
     * String str = "1";
     * str = StringUtility.pad(str, 10, &#39;0&#39;);
     * System.out.println(str); //"0000000001"が出力
     * }</pre>
     *
     * @param str 対象文字列
     * @param totalLen padding後の総文字数
     * @param chr paddingする文字
     * @return String 変換後の文字列
     */
    public static String pad(String str, int totalLen, char chr) {
        // 文字列長チェック
        if (str.length() > totalLen) {
            return str;
        }
        int len = str.length();
        StringBuffer sb = new StringBuffer();
        for (int i = len; i < totalLen; i++) {
            sb.append(chr);
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * 数字を，指定された総文字数に，<br>
     * 指定された文字で右詰めによりpaddingします。<br>
     * 例:1→0000000001
     * <br><br>
     * <br>【Example】
     * <pre>{@literal
     * //1→0000000001
     * int intStr = 1;
     * str = StringUtility.pad(intStr, 10, &#39;0&#39;);
     * System.out.println(str); //"0000000001"が出力
     * }</pre>
     *
     * @param intStr 対象数値
     * @param totalLen padding後の総文字数
     * @param chr paddingする文字
     * @return String 変換後の文字列
     */
    public static String pad(int intStr, int totalLen, char chr) {
        return StringUtility.pad(String.valueOf(intStr), totalLen, chr);
    }
}
