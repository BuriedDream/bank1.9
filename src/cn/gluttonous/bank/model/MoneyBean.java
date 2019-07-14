package cn.gluttonous.bank.model;

/**
 * @title: bank1.9
 * @Description:
 * @Author: xfm
 * @Date: 2019/7/14
 * @Version: 1.9
 **/
public class MoneyBean {

    private double money;

    public MoneyBean(double money){
        this.money = money;
    }

    public MoneyBean(){
        this.money = 0;
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public int hashCode() {
        return (int)this.money;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == null){
            return false;
        }

        if(this == obj){
            return true;
        }

        if(obj instanceof MoneyBean){
            MoneyBean anObj = (MoneyBean)obj;
            if(this.money == anObj.money){
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
