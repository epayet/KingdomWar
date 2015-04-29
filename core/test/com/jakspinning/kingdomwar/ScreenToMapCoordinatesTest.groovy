package com.jakspinning.kingdomwar

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.jakspinning.kingdomwar.component.MapComponent
import spock.lang.Shared
import spock.lang.Specification

import static com.jakspinning.kingdomwar.map.HexGridHelper.getWorldCoordinatesFromScreenCoordinates
import static com.jakspinning.kingdomwar.map.HexGridHelper.getTileCoordinatesFromWorldCoordinates

/**
 * Created by Eptwalabha on 16/04/15.
 *
 * note: Here 'world' refers to the origin (0, 0)
 */
class ScreenToMapCoordinatesTest extends Specification {

    @Shared int screenWidth = 600
    @Shared int screenHeight = 400
    @Shared Vector3 cameraPosition
    @Shared int touchX = 0
    @Shared int touchY = 0
    @Shared float cameraZoom = 1f

    def setup() {
        screenWidth = 600
        screenHeight = 400
        cameraPosition = new Vector3()
        touchX = 0
        touchY = 0
        cameraZoom = 1f
    }

    def "screen to world when touch @center and camera @(0, 0)"() {
        given:
        touchX = 300
        touchY = 200

        when: "user clicks/touches the center of the screen"
        Vector2 worldCoordinates = getWorldCoordinatesFromScreenCoordinates(touchX, touchY, screenWidth, screenHeight, cameraPosition)

        then: "it should calculate the world's coordinates"
        worldCoordinates.x == 0;
        worldCoordinates.y == 0;
    }

    def "screen to world when touch @center and camera @(-50, -50)"() {
        given:
        touchX = 300
        touchY = 200
        cameraPosition.x = -50
        cameraPosition.y = -50

        when: "user clicks/touches the center of the screen"
        Vector2 worldCoordinates = getWorldCoordinatesFromScreenCoordinates(touchX, touchY, screenWidth, screenHeight, cameraPosition)

        then: "it should calculate the world's coordinates"
        worldCoordinates.x == -50;
        worldCoordinates.y == -50;
    }

    def "screen to world when touch @(450, 100) and camera @(0, 0)"() {
        given:
        touchX = 450
        touchY = 100

        when: "user clicks/touches the right-top part of the screen"
        Vector2 worldCoordinates = getWorldCoordinatesFromScreenCoordinates(touchX, touchY, screenWidth, screenHeight, cameraPosition)

        then: "it should calculate the world's coordinates"
        worldCoordinates.x == 150;
        worldCoordinates.y == 100;
    }

    def "screen to world when touch @(450, 100) and camera @(-50, 100)"() {
        given:
        touchX = 450
        touchY = 100
        cameraPosition.x = -50
        cameraPosition.y = 100

        when: "user clicks/touches the right-top part of the screen"
        Vector2 worldCoordinates = getWorldCoordinatesFromScreenCoordinates(touchX, touchY, screenWidth, screenHeight, cameraPosition)

        then: "it should calculate the world's coordinates"
        worldCoordinates.x == 100;
        worldCoordinates.y == 200;
    }
}