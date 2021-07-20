import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Result {
    public long productId;
    public int sellerId;
    public String oriMinPrice;
    public String oriMaxPrice;
    public int promotionId;
    public int startTime;
    public int endTime;
    public int phase;
    public String productTitle;
    public String minPrice;
    public String maxPrice;
    public String discount;
    public String orders;
    public String productImage;
    public String productDetailUrl;
    public String shopUrl;
    public String trace;
    public String totalTranpro3;
    public String productPositiveRate;
    public String productAverageStar;
    public int itemEvalTotalNum;
}
