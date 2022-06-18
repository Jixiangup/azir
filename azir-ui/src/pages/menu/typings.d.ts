declare namespace Menu {

  type TreeData = {
    title: string;
    value: number | undefined;
    children: TreeData[] | null | undefined;
  }

}
