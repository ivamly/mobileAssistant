import {
  AddNoteIcon,
  CopyDocumentIcon,
  DeleteDocumentIcon,
  EditDocumentIcon,
} from "@/components/icons";
import DefaultLayout from "@/layouts/default";
import workSpaces from "@/mock";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  Divider,
  Drawer,
  DrawerBody,
  DrawerContent,
  DrawerFooter,
  DrawerHeader,
  Dropdown,
  DropdownItem,
  DropdownMenu,
  DropdownSection,
  DropdownTrigger,
  Image,
  Input,
  Listbox,
  ListboxItem,
  ScrollShadow,
  useDisclosure,
  User,
} from "@heroui/react";
import clsx from "clsx";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const iconClasses =
  "text-xl text-default-500 pointer-events-none flex-shrink-0";
export default function Workspace() {
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const [workSpace] = useState(workSpaces[0]);
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editName, setEditName] = useState("");
  const [updatedUsers, setUpdatedUsers] = useState<
    typeof workSpace.participants
  >([]);
  useEffect(() => {
    if (!isOpen) {
      setUpdatedUsers([]);
      setEditingId(null);
      setEditName("");
    }
  }, [isOpen]);

  const params = useParams();

  const handleApiSaveChanges = (closeHandler: () => void) => {
    console.log(updatedUsers);
    closeHandler();
  };

  const handleEditStart = (participant: (typeof workSpace.participants)[0]) => {
    setEditingId(participant.id);
    setEditName(participant.name);
  };

  const handleEditSave = () => {
    if (!editName.trim() || !editingId) return;
    setUpdatedUsers((prev) => {
      const index = prev.findIndex((u) => u.id === editingId);
      if (index !== -1) {
        const newUsers = [...prev];
        newUsers[index] = { ...newUsers[index], name: editName };
        return newUsers;
      } else {
        return [...prev, { id: editingId, name: editName }];
      }
    });
    setEditingId(null);
    setEditName("");
  };

  return (
    <DefaultLayout>
      {" "}
      <Drawer isOpen={isOpen} onOpenChange={onOpenChange}>
        <DrawerContent>
          {(onClose) => (
            <>
              <DrawerHeader className="flex flex-col gap-1">
                Участники встречи
              </DrawerHeader>
              <DrawerBody>
                <Listbox>
                  {workSpace.participants.map((participant) => {
                    const userFromInnerUpdated = updatedUsers.find(
                      (u) => u.id === participant.id
                    );

                    return (
                      <ListboxItem
                        key={participant.id}
                        className="flex items-center justify-between gap-2"
                        endContent={
                          editingId !== participant.id && (
                            <Button
                              isIconOnly
                              size="sm"
                              variant="light"
                              onPress={() => handleEditStart(participant)}
                            >
                              <EditDocumentIcon />
                            </Button>
                          )
                        }
                      >
                        {editingId === participant.id ? (
                          <Input
                            autoFocus
                            startContent={<User name={""} />}
                            value={editName}
                            onChange={(e) => setEditName(e.target.value)}
                            className="w-full"
                            endContent={
                              <Button
                                isIconOnly
                                size="sm"
                                color="primary"
                                variant="flat"
                                onPress={() => handleEditSave()}
                              >
                                <AddNoteIcon />
                              </Button>
                            }
                          />
                        ) : userFromInnerUpdated ? (
                          <User
                            name={userFromInnerUpdated.name}
                            description="Product Designer"
                          />
                        ) : (
                          <User
                            name={participant.name}
                            description="Product Designer"
                          />
                        )}
                      </ListboxItem>
                    );
                  })}
                </Listbox>
              </DrawerBody>
              <DrawerFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Закрыть
                </Button>
                <Button
                  color="primary"
                  onPress={() => handleApiSaveChanges(onClose)}
                >
                  Сохранить изменения
                </Button>
              </DrawerFooter>
            </>
          )}
        </DrawerContent>
      </Drawer>
      <Card className="m-2" shadow="sm">
        <CardHeader className="flex gap-3">
          <Image
            alt="logo"
            height={40}
            radius="sm"
            src="https://loremflickr.com/40/40"
            width={40}
          />
          <div className="flex justify-between w-full">
            <div className="flex flex-col">
              <p className="text-md">{workSpace.name}</p>
              <p className="text-small text-default-500">
                {new Date().toLocaleDateString()}{" "}
                {new Date().toLocaleTimeString()}
              </p>
            </div>
            <Dropdown>
              <DropdownTrigger>
                <Button variant="bordered">Параметры встречи</Button>
              </DropdownTrigger>
              <DropdownMenu variant="faded">
                <DropdownSection showDivider title="Actions">
                  <DropdownItem
                    key="edit"
                    description="Позволяет редактировать участников всстречи"
                    shortcut="⌘⇧E"
                    startContent={<EditDocumentIcon className={iconClasses} />}
                    onPress={onOpen}
                  >
                    Редактировать участников
                  </DropdownItem>
                  <DropdownItem
                    key="copy"
                    description="Скопирует ссылку на встречу"
                    shortcut="⌘C"
                    startContent={<CopyDocumentIcon className={iconClasses} />}
                  >
                    Копировать ссылку
                  </DropdownItem>
                </DropdownSection>
                <DropdownSection>
                  <DropdownItem
                    key="delete"
                    className="text-danger"
                    shortcut="⌘⇧D"
                    startContent={
                      <DeleteDocumentIcon
                        className={clsx(iconClasses, "text-danger")}
                      />
                    }
                  >
                    Удалить встречу
                  </DropdownItem>
                </DropdownSection>
              </DropdownMenu>
            </Dropdown>
          </div>
        </CardHeader>
        <Divider />
        <CardBody>
          <ScrollShadow className="flex-1 p-4">
            <div className="flex flex-col gap-4 w-full">
              {workSpace.dialog.map(({ author, id, speech }, i, arr) => (
                <div
                  key={id}
                  className={`flex gap-3 w-full flex-col items-start`}
                >
                  <User description="Product Designer" name={author.name} />
                  <div className={`flex flex-col max-w-full`}>
                    <div className={`rounded-lg p-3 flex flex-1 w-full`}>
                      <p className="text-sm w-full text-wrap">{speech}</p>
                    </div>
                    <span className="mt-1 text-xs text-default-400">
                      {new Date(0).toLocaleTimeString()}
                    </span>
                  </div>
                  <Divider hidden={i === arr.length - 1} />
                </div>
              ))}
            </div>
          </ScrollShadow>
        </CardBody>
      </Card>
    </DefaultLayout>
  );
}
