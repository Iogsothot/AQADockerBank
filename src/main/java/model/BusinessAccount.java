package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessAccount extends BaseAccount {
    private long ogrn;

    public BusinessAccount(long id, long ogrn) {
        super(id, 2);
        this.ogrn = ogrn;
    }
}