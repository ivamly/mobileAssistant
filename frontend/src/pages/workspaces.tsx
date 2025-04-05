import DefaultLayout from "@/layouts/default";
import workSpaces from "@/mock";
import {
  Card,
  CardHeader,
  CardBody,
  Image,
  Divider,
  Link,
  Accordion,
  AccordionItem,
  User,
  Listbox,
  ListboxItem,
  ScrollShadow,
  Avatar,
  Chip,
  Spacer,
  Button,
} from "@heroui/react";

const getStatusColor = (status: string) => {
  switch (status) {
    case "pending":
      return "warning";
    case "in-progress":
      return "primary";
    case "completed":
      return "success";
    default:
      return "default";
  }
};

const statuses = ["pending", "in-progress", "completed", "success"];
const getRandimStatus = () =>
  statuses[Math.floor(Math.random() * statuses.length)];
export default function Workspaces() {
  return (
    <DefaultLayout>
      <div className="text-2xl font-semibold mb-10 flex justify-between">
        <div>Рабочее пространство</div>
        <div>
          <Button size="sm">
            <Link
              isExternal
              className="flex items-center gap-1 text-current"
              href="/create"
              target="_self"
            >
              Новое пространство
            </Link>
          </Button>
        </div>
      </div>
      <Divider />
      <Spacer />
      {workSpaces.map(({ id, name, participants, summary, tasks }) => (
        <Card className="m-2" shadow="sm" key={id}>
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
                <p className="text-md">{name}</p>
                <p className="text-small text-default-500">
                  {new Date().toLocaleDateString()}{" "}
                  {new Date().toLocaleTimeString()}
                </p>
              </div>
              <Link href={`workspace/${id}`} className="text-md">
                Подробнее
              </Link>
            </div>
          </CardHeader>
          <Divider />
          <CardBody>
            <Accordion fullWidth>
              <AccordionItem
                key="1"
                subtitle="Нажмите чтобы открыть"
                title="Спикеры"
              >
                <Listbox>
                  {participants.map(({ id, name }) => (
                    <ListboxItem key={id}>
                      <User description="Product Designer" name={name} />
                    </ListboxItem>
                  ))}
                </Listbox>
              </AccordionItem>
              <AccordionItem
                key="2"
                subtitle="Нажмите чтобы открыть"
                title="Выжимка"
              >
                {" "}
                <ScrollShadow className="flex-1 p-4">
                  <div className="flex flex-col gap-4 w-full">
                    {summary.map(({ author, id, speech }, i, arr) => (
                      <div
                        key={id}
                        className={`flex gap-3 w-full flex-col items-start`}
                      >
                        <User
                          description="Product Designer"
                          name={author.name}
                        />
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
              </AccordionItem>
              <AccordionItem
                key="3"
                subtitle="Нажмите чтобы открыть"
                title="Задачи"
              >
                <ScrollShadow className="flex-1 p-4">
                  <div className="flex flex-col gap-4">
                    {tasks.map((task) => (
                      <Card key={task.id} className="p-4">
                        <div className="flex flex-col gap-2">
                          <div className="flex items-start justify-between">
                            <h4 className="text-base font-medium">
                              {task.name}
                            </h4>
                            <Chip
                              size="sm"
                              color={getStatusColor(getRandimStatus())}
                              variant="flat"
                            >
                              {getRandimStatus()}
                            </Chip>
                          </div>
                          <Divider className="my-2" />
                          <div className="flex items-center justify-between">
                            <div className="flex items-center gap-2">
                              <Avatar
                                src={task.author.name}
                                name={task.author.name}
                                size="sm"
                              />
                              <span className="text-sm text-default-500">
                                {task.author.name}
                              </span>
                            </div>
                            <span className="text-xs text-default-400">
                              Due: {new Date(0).toLocaleDateString()}
                            </span>
                          </div>
                        </div>
                      </Card>
                    ))}
                  </div>
                </ScrollShadow>
              </AccordionItem>
            </Accordion>
          </CardBody>
        </Card>
      ))}
    </DefaultLayout>
  );
}
