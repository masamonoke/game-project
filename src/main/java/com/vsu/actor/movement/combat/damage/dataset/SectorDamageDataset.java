package com.vsu.actor.movement.combat.damage.dataset;

import com.vsu.map.Vector2;
import com.vsu.utils.GeometryUtils;
import lombok.Setter;

//TODO: отвязать вычисления от градусов и перейти к радианам
//TODO: проверить, работает ли при отклонении ±90 или переделать под все возможные случаи
@Setter
public class SectorDamageDataset extends CircleDamageDataset {
    private double alphaAngle;
    private double betaAngle;

    public SectorDamageDataset(double alphaAngle, double betaAngle, double radius, Vector2 center) {
        super(radius, center);
        this.alphaAngle = alphaAngle;
        this.betaAngle = betaAngle;
    }

    @Override
    public boolean isDamaged(Vector2 p) {
        //angle может выкинуть исключение
        var local = p.subtract(center);
        var angle = GeometryUtils.findVectorAngleByPoint(local) * 180 / Math.PI;
        boolean isInSector;
        if (alphaAngle < 0) {
            var quadrant = GeometryUtils.findQuadrant(p);
            if (quadrant == 1) {
                isInSector = 0 <= angle && angle <= betaAngle;
            } else if (quadrant == 4) {
                var tmp = 360 - (-alphaAngle);
                isInSector = tmp <= angle && angle <= 360;
            } else {
                isInSector = false;
            }
        } else if (betaAngle > 360) {
            var quadrant = GeometryUtils.findQuadrant(p);
            if (quadrant == 1) {
                var tmp = betaAngle - 360;
                isInSector = 0 <= angle && angle <= betaAngle;
            } else if (quadrant == 4) {
                isInSector = alphaAngle <= angle && angle <= 360;
            } else {
                isInSector = false;
            }
        } else {
            isInSector = alphaAngle <= angle && angle <= betaAngle;
        }
        var isInCircle = super.isDamaged(p);
        return isInSector && isInCircle;
    }

}
