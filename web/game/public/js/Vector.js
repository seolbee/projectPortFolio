class Vector{
    constructor(x, y){
        this.x = x;
        this.y = y;
    }

    normalize(){
        if(this.normall == null){
            let c = Math.sqrt(this.x * this.x + this.y * this.y);
            if(c == 0){
                this.normal = new Vector(0,0);
            }else{
                this.normal = new Vector(this.x / c, this.y / c);
            }
        }
        return this.normal;
    }
}