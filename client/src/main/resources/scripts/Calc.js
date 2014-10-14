// unit

function getSize2() {
  return size + 2;
}

function isUndead() {
  return bRace == RACE_UNDEAD;
}

function calcHP() {
   return Math.ceil((rStr + rStam * 1.5) * size);
}

function calcAP() {
  return 5 + Math.floor(bAgi / 2);
}

//(сила*1,5-(броня-2)*5-(размер-2)*5)/10 Округлить до целых
function calcWalkAPConsume() {
  return Math.round((rStr*1.5 - (bArmor-2)*5 - (bSize-2)*5) / 10);
}