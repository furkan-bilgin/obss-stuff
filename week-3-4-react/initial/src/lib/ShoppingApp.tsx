import { ShoppingList } from './ShoppingList';
import { ShoppingTitle } from './ShoppingTitle';

export interface Basket {
  [itemCategory: string]: ShoppingItem[];
}

export interface ShoppingItem {
  name: string;
}

const basket: Basket = {
  Food: [{ name: 'Apple' }, { name: 'Bread' }, { name: 'Cheese' }],
  Clothes: [{ name: 'Shirt' }, { name: 'Pants' }, { name: 'Hat' }],
  Supplies: [{ name: 'Pen' }, { name: 'Paper' }, { name: 'Glue' }],
};

export function ShoppingApp() {
  return (
    <>
      <ShoppingTitle basket={basket} />
      <ShoppingList basket={basket} />
    </>
  );
}
