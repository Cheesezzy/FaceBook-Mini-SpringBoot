package com.example.Week7.util;

import com.example.Week7.pojos.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


//@Bean   => Is available everywhere in the project
//@Service  => Its obbject is automatically instantiated
//@Component => Anotation for component class it function similar to service

@AllArgsConstructor
@Service
public class ResponseManager<T> {

    public ApiResponse<T> success(T data){
        return new ApiResponse<T>("Request successful", true, data);
    }

    public ApiResponse<T>  error(String errorMessage){
        return new ApiResponse<T>(errorMessage, false, null);
    }
}
