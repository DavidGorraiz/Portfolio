const BtnAddRev = document.getElementById("budget-revenue");
const BtnAddOut = document.getElementById("budget-outgoing");

BtnAddRev.addEventListener("click", AddNewRev);
BtnAddOut.addEventListener("click", AddNewOut);

function AddNewRev(){
    //<div id="container"></div>
    const DivContainer = document.getElementById("cointainer-revenue");
    //<div id="new-container"></div>
    const NewContainer = document.getElementById("new-container-revenue");
    DivContainer.appendChild(NewContainer.cloneNode(true));
}

function AddNewOut(){
    //<div id="container"></div>
    const DivContainer = document.getElementById("cointainer-outgoing");
    //<div id="new-container"></div>
    const NewContainer = document.getElementById("new-container-outgoing");
    DivContainer.appendChild(NewContainer.cloneNode(true));
}
