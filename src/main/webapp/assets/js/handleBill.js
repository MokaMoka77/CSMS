/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
//<i class="fa-solid fa-circle-xmark"></i>

const tableProcess = document.getElementById('bill-process');
const tableComplete = document.getElementById('bill-complete');
const tbodyProcess = tableProcess.querySelector('tbody');
const tbodyComplete = tableComplete.querySelector('tbody');
const totalQuantity = document.querySelector('.quantity');
const totalMoney = document.querySelector('.money');
let listDrink = [];
const date = document.querySelector('.date');
const time = document.querySelector('.time');


function getInfoDrink({id,name,price}){
    console.log(id);
    if(!tableProcess.innerHTML.includes(`>${id}<`)){
        const trTagProcess = document.createElement('tr');
        trTagProcess.innerHTML = `
            
                <th scope="row">${id}</th>
                <td>${name}</td>
                <td>${price}</td>
                <td><input name='quanlity' value='1' type="number" oninput="validatePositiveNumber(this,'${id}')"></td>
                <td onClick="removeOutTable(this,'${id}')">
                   <i class="fa-solid fa-circle-xmark close"></i> 
                </td>
            
        `
        listDrink.push({id,name,price,quantity:trTagProcess.querySelector('input').value});
        totalQuantity.innerText = listDrink.length+" drinks";
        totalMoney.innerText = handleTotalPrice()+" vnd";
        
        tbodyProcess.appendChild(trTagProcess);
    }
}

function showContentBill(){
     listDrink.forEach((drink) => {
        const trTagComplete = document.createElement('tr');
        trTagComplete.innerHTML = `           
            <th scope="row">${drink.id}</th>
            <td>${drink.name}</td>
            <td>${drink.price}</td>
            <td>${drink.quantity}</td>            
        `
        tbodyComplete.appendChild(trTagComplete);
     })
    var curDate = new Date();
    date.innerText = `${curDate.getDate()}-${curDate.getMonth()+1}-${curDate.getFullYear()}`;
    time.innerText = `${curDate.getHours()}:${curDate.getMinutes()+1}:${curDate.getSeconds()}`;
    
    document.querySelector('.quantity-bill').innerText = totalQuantity.innerText;
    document.querySelector('.money-bill').innerText = totalMoney.innerText;
}

function handleTotalPrice(){
    return listDrink.reduce((acc, drink , index) => {
        return acc + Number(drink.price)*Number(drink.quantity);
    },0)
}

function validatePositiveNumber(input,id) {
    const value = parseFloat(input.value);
    if (isNaN(value) || value <= 0) {
        input.value = '1'; // Xóa giá trị âm hoặc không hợp lệ
    }else{
         listDrink.forEach((drink)=>{
            drink.quantity = drink.id===id ? value : drink.quantity;
         })
         totalQuantity.innerText = listDrink.reduce((total,drink)=>total+Number(drink.quantity),0)+" drinks";
         totalMoney.innerText = handleTotalPrice()+" vnd";
    }
}

function removeOutTable(param,id){
    tbodyProcess.removeChild(param.parentNode);
    listDrink = listDrink.filter(drink => drink.id !== id);
    totalQuantity.innerText =listDrink.reduce((total,drink)=>total+Number(drink.quantity),0)+" drinks";
    totalMoney.innerText = handleTotalPrice()+ "vnd";
}

function sendBillToServlet(){
    //var curDate = new Date();
    var Orders = [];
    var randomIdBill = Math.floor(Math.random() * 1000000)+"";
    listDrink.forEach(drink => {
        Orders.push(
            {
                bill:{
                    id: randomIdBill,
                    totalPrice: handleTotalPrice(), // Thay bằng tổng thực tế
                    createdAt: getDateTime(),
                },
                drink: {
                    id: drink.id // Thay bằng ID đồ uống thực tế
                },
                quantity: Number(drink.quantity), // Thay bằng số lượng thực tế
                totalPrice: Number(drink.price) * Number(drink.quantity) // Thay bằng tổng giá thực tế
            }
        ) 
    });
    
    //console.log(objectJSON);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "createBill", true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Xử lý phản hồi từ servlet nếu cần
            var response = JSON.parse(xhr.responseText);
            console.log(response);
        }
    };
    xhr.send(JSON.stringify(Orders));
    location.reload();
    //alert('you have save bill successfully!')
//    $.ajax({
//        type: "POST",
//        url: "createBill", // Thay bằng URL của Servlet
//        data: { listSoldDrink: JSON.stringify(['aaa','bbb','ccc']) },
//        success: function(response) {
//            console.log("Data sent successfully.");
//        }
//    });
    
}

function randomIdBill(length) {
    let result = '';
    const characters = '0123456789'; // Chuỗi chứa các chữ số

    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        result += characters.charAt(randomIndex);
    }

    return result;
}

function getDateTime(){
    const jsDate = new Date();

    // Lấy thông tin từ đối tượng Date
    const year = jsDate.getFullYear();
    const month = jsDate.getMonth() + 1; // Tháng trong JavaScript bắt đầu từ 0
    const day = jsDate.getDate();
    const hours = jsDate.getHours();
    const minutes = jsDate.getMinutes();
    const seconds = jsDate.getSeconds();

    // Định dạng ngày tháng theo chuẩn ISO để truyền vào SQL
    return`${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')} ${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
}

function cancelBill(){
    tbodyComplete.innerHTML = '';
    document.querySelector('.quantity-bill').innerText = '0 drinks';
    document.querySelector('.money-bill').innerText = '0 vnd';
}



