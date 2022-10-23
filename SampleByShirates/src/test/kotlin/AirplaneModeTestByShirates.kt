import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import shirates.core.driver.branchextension.ifCheckOFF
import shirates.core.driver.commandextension.checkIsOFF
import shirates.core.driver.commandextension.checkIsON
import shirates.core.driver.commandextension.select
import shirates.core.driver.commandextension.tap
import shirates.core.testcode.UITest

class AirplaneModeTestByShirates : UITest() {

    @Test
    @Order(10)
    @DisplayName("When airplane switch is ON, it should be OFF after tapped")
    fun airplaneModeSwitch() {

        scenario {
            case(1) {
                condition {
                    it.tap("Network & internet")
                    it.select("<Airplane mode>:rightSwitch")
                        .ifCheckOFF {
                            it.tap()
                        }
                    it.checkIsON()
                }.action {
                    it.tap("<Airplane mode>:rightSwitch")
                }.expectation {
                    it.checkIsOFF()
                }
            }
        }
    }

}