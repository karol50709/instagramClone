package com.kb.igClone.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImagesSaveResultBean {

    private String imageMongoId;

    private String thumbnailMongoId;
}
