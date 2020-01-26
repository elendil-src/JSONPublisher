package com.elendil.app.demo;

/**
 * Represents an problem with the ProductModelImpl that prevents the operation from completing.
 */
class ProductModelException extends Exception
{
    ProductModelException(String message) {
        super(message);
    }
}
