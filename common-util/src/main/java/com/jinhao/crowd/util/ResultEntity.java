package com.jinhao.crowd.util;

/**
 * Created on 2020/10/12.
 *
 * @author Valar Morghulis
 */
public class ResultEntity<T> {


    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MESSAGE = "NO_MESSAGE";
    public static final String NO_DATA = "NO_DATA";


    private String result;
    private String message;
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static <Type> ResultEntity<Type> successWithoutData(){
        return new ResultEntity<Type>(SUCCESS,NO_MESSAGE,null);
    }

    public static <Type> ResultEntity<Type> successWithData(Type data){
        return new ResultEntity<Type>(SUCCESS,NO_MESSAGE,data);
    }

    public static <Type> ResultEntity<Type> failed(String message){
        return new ResultEntity<>(FAILED,message,null);
    }
}
