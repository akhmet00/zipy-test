import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Result {

    public Long productId;
    public Integer sellerId;
    public String oriMinPrice;
    public String oriMaxPrice;
    public Integer promotionId;
    public Integer startTime;
    public Integer endTime;
    public Integer phase;
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
    public Integer itemEvalTotalNum;

}
