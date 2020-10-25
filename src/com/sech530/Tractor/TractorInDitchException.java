package com.sech530.Tractor;

public class TractorInDitchException extends RuntimeException {
    TractorInDitchException(){
        super("Трактор вышел за границы поля!");
    }
}