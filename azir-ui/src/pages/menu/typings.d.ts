import type {Callbacks} from "rc-field-form/lib/interface";
import type {FormInstance} from "antd/lib/form/hooks/useForm";

declare namespace Menu {

  type TreeData = {
    title: string;
    value: number | undefined;
    children: TreeData[] | null | undefined;
  }

  type EditProps = {
    visible: boolean;
    setVisible: Callbacks<Values>['setVisible'];
    menus: API.Menu[] | undefined;
    refreshMenus: Callbacks<Values>['setVisible'];
    selectMenu: Callbacks<Values>['selectMenu'];
    form: FormInstance<Values>;
  }

}
