/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.services.entityForServices;

/**
 *
 * @author phamt
 * @param <B>
 * @param <S>
 * @param <Sl>
 */
public class BookScheSlot<B, S, Sl> {
    private final B book;
    private final S sche;
    private final Sl slot;

    public BookScheSlot(B book, S sche, Sl slot) {
        this.book = book;
        this.sche = sche;
        this.slot = slot;
    }

    public B getBook() {
        return book;
    }

    public S getSche() {
        return sche;
    }

    public Sl getSlot() {
        return slot;
    }
}

