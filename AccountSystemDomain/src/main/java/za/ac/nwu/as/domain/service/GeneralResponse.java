package za.ac.nwu.as.domain.service;

import java.io.Serializable;
import java.util.Objects;

public class GeneralResponse<T> implements Serializable {

    private boolean isSuccessful;
    private transient T payload;

    public GeneralResponse(boolean isSuccessful, T payload) {
        this.isSuccessful = isSuccessful;
        this.payload = payload;
    }

    public GeneralResponse() {
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralResponse<?> that = (GeneralResponse<?>) o;
        return isSuccessful == that.isSuccessful && Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSuccessful, payload);
    }

    @Override
    public String toString() {
        return "GeneralResponse{" +
                "isSuccessful=" + isSuccessful +
                ", payload=" + payload +
                '}';
    }
}
