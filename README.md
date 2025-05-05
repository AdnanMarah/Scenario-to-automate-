# 🌟 Almosafer.com Automation Practice Tests

This project automates test scenarios on [almosafer.com](https://www.almosafer.com/en?ncr=1) using **Java**, **Selenium WebDriver**, and **TestNG**.

---

## 📄 Table of Contents

- [Project Setup](#project-setup)
- [Technologies Used](#technologies-used)
- [Test Cases Overview](#test-cases-overview)
- [Detailed Test Scenarios](#detailed-test-scenarios)
- [Notes](#notes)

---

## ✅ Project Setup

- Install [Java JDK 8+](https://adoptium.net/)
- Install [TestNG](https://testng.org/)
- Download [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/) and set it in your system path
- Add Selenium & TestNG libraries to your classpath (or use Maven)
- Clone or import the Java files into Eclipse/IntelliJ
- Run tests via IDE or TestNG XML

---

## 🛠️ Technologies Used

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **ChromeDriver**
- **Maven** (optional)

---

## 🧪 Test Cases Overview

| # | Test Name | Description |
|--:|:----------|:------------|
| 1 | CheckTheDefaultLanguageIsEnglish | Verifies that the default language is English |
| 2 | CheckDefaultCurrencyIsSAR | Verifies that the default currency is SAR |
| 3 | CheckTheContactNumber | Checks the default contact number |
| 4 | TestVerifyQitafLogoInFooter | Verifies that Qitaf logo appears in footer |
| 5 | checkHotelsTabNotSelectedByDefault | Ensures Hotels tab is not selected by default |
| 6 | checkDepartureDateIsDayAfterTomorrow | Verifies departure date is tomorrow |
| 7 | checkReturnDateIsDayAfterTomorrow | Verifies return date is after tomorrow |
| 8 | changeLanguageRandomly | Randomly changes the language |
| 9 | switchToHotelTab | Switches to Hotels tab and types a random city |
|10 | TestSelectRandomStayOption | Randomly selects stay options |
|11 | clickOnSearchHotelsButton | Clicks on search button |
|12 | waitForHotelsResultsToLoad | Waits for and prints hotel results |

---

## 🔍 Detailed Test Scenarios

### 1. 🌐 CheckTheDefaultLanguageIsEnglish
- Retrieves the `lang` attribute of the `<html>` element
- Verifies it's set to `'en'`

---

### 2. 💱 CheckDefaultCurrencyIsSAR
- Checks the top currency display
- Asserts it equals `'SAR'`

---

### 3. ☎️ CheckTheContactNumber
- Verifies that the contact number is `+966554400000`

---

### 4. 🟣 TestVerifyQitafLogoInFooter
- Scrolls to the footer
- Verifies Qitaf logo is displayed

---

### 5. 🏨 checkHotelsTabNotSelectedByDefault
- Ensures Hotels tab class doesn’t include `active`

---

### 6. 📅 checkDepartureDateIsDayAfterTomorrow
- Compares current date to the selected departure date (today + 1)

---

### 7. 🛬 checkReturnDateIsDayAfterTomorrow
- Compares current date to the return date (today + 2)

---

### 8. 🔁 changeLanguageRandomly
- Randomly switches between Arabic and English
- Confirms the language toggle

---

### 9. 🏙️ switchToHotelTab
- Switches to Hotel tab
- Types in a city name (based on language)

---

### 10. 🛏️ TestSelectRandomStayOption
- Randomly selects number of rooms/adults from dropdown

---

### 11. 🔍 clickOnSearchHotelsButton
- Clicks the hotel search button

---

### 12. 📈 waitForHotelsResultsToLoad
- Waits for search results
- Prints number of result cards displayed

---

## 📝 Notes

- `Thread.sleep()` used for delays – adjust as needed
- Website tested: `https://www.almosafer.com/en?ncr=1`
- Use latest version of ChromeDriver matching your browser
- Only one test is marked `enabled=true` by default
- You can run all via TestNG suite or manually enable each test

---

# 🚀 Happy Testing! 🚀
