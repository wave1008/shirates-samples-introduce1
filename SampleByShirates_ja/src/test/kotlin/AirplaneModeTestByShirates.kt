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
    @DisplayName("機内モードスイッチがONの場合にタップするとOFFになること")
    fun airplaneModeSwitch() {

        scenario {
            case(1) {
                condition {
                    // 「ネットワークとインターネット」をタップする
                    it.tap("ネットワークとインターネット")
                    // 機内モードスイッチの状態を取得する。OFFの場合はタップしてONにする
                    it.select("<機内モード>:rightSwitch")
                        .ifCheckOFF {
                            it.tap()
                        }
                    it.checkIsON()
                }.action {
                    // 機内モードスイッチをタップする
                    it.tap("<機内モード>:rightSwitch")
                }.expectation {
                    // 機内モードスイッチがOFFであることを検証する
                    it.checkIsOFF()
                }
            }
        }
    }

}