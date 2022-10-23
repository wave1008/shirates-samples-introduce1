import io.appium.java_client.android.AndroidDriver
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class AirplaneModeTestByAppium {

    private fun getDriver(): RemoteWebDriver {

        val caps = DesiredCapabilities()
        with(caps) {
            setCapability("appium:automationName", "UiAutomator2")
            setCapability("platformName", "Android")
            setCapability("appium:platformVersion", "12")
            setCapability("appium:language", "ja")
            setCapability("appium:locale", "JP")
            setCapability("appium:appPackage", "com.android.settings")
            setCapability("appium:appActivity", "com.android.settings.Settings")
        }
        val driver = AndroidDriver(URL("http://127.0.0.1:4723/"), caps)
        driver.setSetting("enforceXPath1", true)

        return driver
    }

    @Test
    @Order(10)
    @DisplayName("機内モードスイッチがOFFの場合にタップするとONになること")
    fun airplaneModeSwitch() {

        val driver = getDriver()
        var e: WebElement

        // 「ネットワークとインターネット」をタップする
        e = driver.findElement(By.xpath("//*[@text='ネットワークとインターネット']"))
        e.click()
        Thread.sleep(1000)

        // 機内モードスイッチの状態を取得する。OFFの場合はタップしてONにする
        e = driver.findElement(By.xpath("//*[@text='機内モード']/following::*[@resource-id='android:id/switch_widget']"))
        if (e.getDomAttribute("checked") == "false") {
            e.click()
            Thread.sleep(1000)
            e =
                driver.findElement(By.xpath("//*[@text='機内モード']/following::*[@resource-id='android:id/switch_widget']"))
            assertThat(e.getDomAttribute("checked")).isEqualTo("true")
        }

        // 機内モードスイッチをタップする
        e.click()
        Thread.sleep(1000)

        // 機内モードスイッチがOFFであることを検証する
        e = driver.findElement(By.xpath("//*[@text='機内モード']/following::*[@resource-id='android:id/switch_widget']"))
        val checkState = e.getDomAttribute("checked")
        assertThat(checkState).isEqualTo("false")
    }

}