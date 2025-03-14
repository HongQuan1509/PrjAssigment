// calendar.js

document.addEventListener("DOMContentLoaded", function () {
  const yearSelect = document.getElementById("yearSelect");
  const monthSelect = document.getElementById("monthSelect");

  const currentDate = new Date();
  const currentYear = currentDate.getFullYear();
  const currentMonth = currentDate.getMonth() + 1;

  // Khởi tạo combobox year và month
  initYearMonthSelects(yearSelect, monthSelect, currentYear, currentMonth);

  // Render lần đầu
  renderCalendar(currentYear, currentMonth);

  // Sự kiện thay đổi năm / tháng
  yearSelect.addEventListener("change", () => {
    const year = parseInt(yearSelect.value);
    const month = parseInt(monthSelect.value);
    renderCalendar(year, month);
  });

  monthSelect.addEventListener("change", () => {
    const year = parseInt(yearSelect.value);
    const month = parseInt(monthSelect.value);
    renderCalendar(year, month);
  });
});

/**
 * Khởi tạo danh sách năm và tháng cho dropdown
 */
function initYearMonthSelects(yearSelect, monthSelect, currentYear, currentMonth) {
  const minYear = currentYear - 5;
  const maxYear = currentYear + 5;

  // Years
  for (let y = minYear; y <= maxYear; y++) {
    const option = document.createElement("option");
    option.value = y;
    option.textContent = y;
    if (y === currentYear) option.selected = true;
    yearSelect.appendChild(option);
  }

  // Months
  for (let m = 1; m <= 12; m++) {
    const option = document.createElement("option");
    option.value = m;
    option.textContent = m.toString().padStart(2, '0');
    if (m === currentMonth) option.selected = true;
    monthSelect.appendChild(option);
  }
}

/**
 * Render bảng lịch theo tháng và năm
 */
function renderCalendar(year, month) {
  const calendarBody = document.getElementById("calendarBody");
  if (!calendarBody) {
    console.error("Không tìm thấy phần tử calendarBody");
    return;
  }

  calendarBody.innerHTML = "";

  const firstDay = new Date(year, month - 1, 1);
  const lastDay = new Date(year, month, 0);

  const totalDays = lastDay.getDate();
  const startDay = (firstDay.getDay() + 6) % 7; // Bắt đầu từ thứ 2 (Monday = 0)

  let dayCounter = 1;

  // Tạo dòng đầu tiên
  let row = document.createElement("tr");

  // Ô trống trước ngày 1
  for (let i = 0; i < startDay; i++) {
    let emptyCell = document.createElement("td");
    emptyCell.className = "empty-day";
    row.appendChild(emptyCell);
  }

  // Các ngày còn lại của tuần đầu tiên
  for (let i = startDay; i < 7; i++) {
    let cell = createDayCell(year, month, dayCounter);
    row.appendChild(cell);
    dayCounter++;
  }

  calendarBody.appendChild(row);

  // Các dòng còn lại của lịch
  while (dayCounter <= totalDays) {
    row = document.createElement("tr");

    for (let i = 0; i < 7; i++) {
      if (dayCounter > totalDays) {
        let emptyCell = document.createElement("td");
        emptyCell.className = "empty-day";
        row.appendChild(emptyCell);
      } else {
        let cell = createDayCell(year, month, dayCounter);
        row.appendChild(cell);
        dayCounter++;
      }
    }

    calendarBody.appendChild(row);
  }
}

/**
 * Tạo 1 ô ngày cụ thể
 */
function createDayCell(year, month, day) {
  const cell = document.createElement("td");

  // Fake ngày nghỉ (ví dụ)
  const leaveDays = [5, 12, 20]; // Đây là những ngày nghỉ giả lập
  let cellClass = "working-day";

  if (leaveDays.includes(day)) {
    cellClass = "leave-day";
  }

  // Kiểm tra có phải hôm nay không
  const today = new Date();
  const isToday = today.getFullYear() === year &&
                  today.getMonth() === (month - 1) &&
                  today.getDate() === day;

  if (isToday) {
    cellClass += " today";
  }

  cell.className = cellClass;
  cell.textContent = day;

  // Thêm tooltip hoặc hành động click (nếu cần)
  cell.title = `Ngày ${day}/${month}/${year}`;

  // Nếu muốn thêm sự kiện click:
  // cell.addEventListener("click", () => alert(`Clicked: ${day}/${month}/${year}`));

  return cell;
}
