       function populateCalendar() {
            const calendar = document.getElementById('calendar');
            const todayInfo = document.getElementById('today-info').textContent;
            const header = document.getElementById('calendar-header');

            const today = new Date();
            const year = today.getFullYear();
            const month = today.getMonth();
            const daysInMonth = new Date(year, month + 1, 0).getDate();
			const todayDayNum = today.getDate();
			
			const calDays = document.getElementsByClassName("calDay");
			const todayYYYYMM = todayInfo.substring(4,8)+todayInfo.substring(9,11);
			const YYYYMMarr = header.textContent.split(' ');
			const YYYYMM = (YYYYMMarr[2]).substring(0,4)+YYYYMMarr[3].split('월')[0];
			for(let i=0;i<calDays.length;i++){
				console.log("calDays[i] : "+calDays[i].textContent);
				console.log("todayInfo YYYYMM= "+todayYYYYMM);
				console.log("year+month = "+year + month);
				
				console.log("spot 1 :"+ todayInfo.substring(9,11));
				console.log("spot 2 :"+ Number(YYYYMMarr[3].split('월')[0]));
				console.log("spot 3 :"+ Number(todayInfo.substring(4,8)));
				console.log("spot 4 :"+ YYYYMMarr[2].substring(0,4));
				

				/* 오늘 날짜 하트 그리기 시작 */
				if(calDays[i].textContent==todayDayNum && 
				Number(todayInfo.substring(9,11)) == Number(YYYYMMarr[3].split('월')[0]) &&
				todayInfo.substring(4,8) == YYYYMMarr[2].substring(0,4)
				){	
					const dayDiv = document.createElement('div');
					//dayDiv.innerHTML += '<br>Valentine\'s Day!';
					//dayDiv.classList.add('day-heart');
					calDays[i].classList.add('day-heart');
					//calDays[i].classList.add('day-star');
					//calDays[i].appendChild(dayDiv);
					console.log("todayNum : "+todayDayNum);
				}
				/* 오늘 날짜 하트 그리기 끝 */
				
				
				/* 달력 날짜 이후인 날들은 일기 못쓰게 하기 시작*/
				if(Number(todayInfo.substring(4,8)) < Number(YYYYMMarr[2].substring(0,4))){ //달력 년도가 현재보다 미래
					calDays[i].classList.add('disableLink');
				}
				else if( Number(todayInfo.substring(4,8)) == Number(YYYYMMarr[2].substring(0,4)) &&
					Number(todayInfo.substring(9,11)) < Number(YYYYMMarr[3].split('월')[0]) //같은 해, 월이 현재보다 미래
					){
						calDays[i].classList.add('disableLink');
					} 
				else if(Number(todayInfo.substring(4,8)) == Number(YYYYMMarr[2].substring(0,4)) &&
					Number(todayInfo.substring(9,11)) == Number(YYYYMMarr[3].split('월')[0]) &&
					Number(calDays[i].textContent)>Number(todayDayNum)){
					calDays[i].classList.add('disableLink');
				}
				/* 달력 날짜 이후인 날들은 일기 못쓰게 하기 끝*/
				
				
				//달력상의 년월 : YYYYMM
				//달력상 일 calDays[i].textContent 
				
			}
			
			
            //header.textContent = `Gunhee's: ${year} ${today.toLocaleString('default', { month: 'long' })}`;
            //todayInfo.textContent = `오늘은 ${today.getFullYear()}-${(today.getMonth() + 1).toString().padStart(2, '0')}-${today.getDate().toString().padStart(2, '0')}`;
/*
            calendar.innerHTML = '';

            const firstDay = new Date(year, month, 1).getDay();


            for (let i = 0; i < firstDay; i++) {
                const dayDiv = document.createElement('div');
                dayDiv.classList.add('day');
                calendar.appendChild(dayDiv);
            }
            for (let day = 1; day <= daysInMonth; day++) {
                const dayDiv = document.createElement('div');
                dayDiv.classList.add('day');
                dayDiv.textContent = day;
				
                if (day === 1 || day === 2 || day === 3) {
                    dayDiv.classList.add('today');
                }
                if (day === 5) {
                    dayDiv.classList.add('heart');
                }
                if (day === 14) {
                    dayDiv.innerHTML += '<br>Valentine\'s Day!';
                }
                if (day === 28) {
                    dayDiv.innerHTML += '<br><span style="background-color: #99ff99;">우기생일</span>';
                }
                if (day === 29) {
                    dayDiv.innerHTML += '<br><span style="background-color: #9999ff;">사수생일</span>';
                }
                if (day === 30) {
                    dayDiv.innerHTML += '<br><span style="background-color: #ff9999;">거사생일</span>';
                }

                calendar.appendChild(dayDiv);
            }*/
        }
		
        document.addEventListener('DOMContentLoaded', populateCalendar);
        
   /**
 * 

    calendar.render();
  });
/**
 

const months = [
  'January',
  'February',
  'March',
  'April',
  'May',
  'June',
  'July',
  'August',
  'September',
  'October',
  'November',
  'December',
];
* 
function addDiaryIconToDay(id){
	id.setAttribute
}

        document.addEventListener('DOMContentLoaded', populateCalendar);
   /**
 * 
 */