package guru.sfg.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto implements Serializable {

  @Serial private static final long serialVersionUID = 5706374617046143969L;

  @Null
  private UUID id;

  @Null
  private Integer version;

  @Null
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:mm:ss.SSSSSSXXXXX", shape = Shape.STRING)
  private OffsetDateTime createdDate;

  @Null
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:mm:ss.SSSSSSXXXXX", shape = Shape.STRING)
  private OffsetDateTime lastModifiedDate;

  @NotBlank
  private String beerName;

  @NotNull
  @Valid
  private BeerStyleEmum beerStyle;

  @NotNull
  private String upc;

  @NotNull
  @Positive
  @JsonFormat(shape = Shape.STRING)
  private BigDecimal price;

  private Integer quantityOnHand;
}
