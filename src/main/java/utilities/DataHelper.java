package utilities;

public class DataHelper {

    private int searchColumnNum;
    private String searchText;
    private int returnColumnText;
    private String expectedText;

    public DataHelper(int searchColumnNum, String searchText, int returnColumnText, String expectedText) {
        this.searchColumnNum = searchColumnNum;
        this.searchText = searchText;
        this.returnColumnText = returnColumnText;
        this.expectedText = expectedText;
    }

    public int getSearchColumnNum() {
        return searchColumnNum;
    }

    public void setSearchColumnNum(int searchColumnNum) {
        this.searchColumnNum = searchColumnNum;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getReturnColumnText() {
        return returnColumnText;
    }

    public void setReturnColumnText(int returnColumnText) {
        this.returnColumnText = returnColumnText;
    }

    public String getExpectedText() {
        return expectedText;
    }

    public void setExpectedText(String expectedText) {
        this.expectedText = expectedText;
    }
}
