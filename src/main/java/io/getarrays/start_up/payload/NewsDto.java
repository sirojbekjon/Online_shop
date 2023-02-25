package io.getarrays.start_up.payload;

import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
public class NewsDto {

    private String name;

    private TextArea text;

    private List<Long> fileUploadId;
}
