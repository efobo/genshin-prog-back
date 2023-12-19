package com.group.genshinProg.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO implements Serializable {
    public String response;



    public String getResponse() {return response;}
    public void setResponse(String response) {this.response = response;}
}
